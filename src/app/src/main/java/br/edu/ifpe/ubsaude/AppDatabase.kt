package br.edu.ifpe.ubsaude

import android.content.Context
import androidx.room.migration.Migration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Fronteira de Arquivo: Banco local Room usado pelas funcionalidades do aplicativo.
 */
@Database(
    entities = [Cadastro::class, Agendamento::class, Servico::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    // Disponibiliza o DAO usado para ler e gravar cadastros.
    abstract fun cadastroDao(): CadastroDao

    // Disponibiliza o DAO usado para ler e gravar agendamentos.
    abstract fun agendamentoDao(): AgendamentoDao

    // Disponibiliza o DAO usado para consultar serviços.
    abstract fun servicoDao(): ServicoDao

    companion object {
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            // Adiciona a tabela de agendamentos sem apagar os cadastros existentes.
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `Agendamento` (" +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`nome` TEXT NOT NULL, " +
                        "`data` TEXT NOT NULL, " +
                        "`cartaoSUS` TEXT NOT NULL, " +
                        "`horario` TEXT NOT NULL, " +
                        "`servico` TEXT NOT NULL)"
                )
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            // Adiciona serviços ao banco e mantém a lista demonstrativa já exibida pelo app.
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `Servico` (" +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`nome` TEXT NOT NULL, " +
                        "`descricao` TEXT NOT NULL, " +
                        "`horario` TEXT NOT NULL)"
                )
                inserirServicosDeExemplo(database)
            }
        }

        private val DATABASE_CALLBACK = object : RoomDatabase.Callback() {
            // Preenche a tabela recém-criada com os exemplos já usados na interface.
            override fun onCreate(database: SupportSQLiteDatabase) {
                super.onCreate(database)
                inserirServicosDeExemplo(database)
            }
        }

        private val SERVICOS_DE_EXEMPLO = listOf(
            Servico(nome = "Clínico Geral", descricao = "Segunda a Sexta", horario = "08h às 17h"),
            Servico(nome = "Odontologia", descricao = "Terça e Quinta", horario = "09h às 16h"),
            Servico(nome = "Vacinação", descricao = "Segunda a Sexta", horario = "08h às 17h"),
            Servico(nome = "Pré-Natal", descricao = "Quarta-feira", horario = "08h às 12h")
        )

        @Volatile
        private var instance: AppDatabase? = null

        // Insere os dados locais de demonstração sem substituir registros existentes.
        private fun inserirServicosDeExemplo(database: SupportSQLiteDatabase) {
            SERVICOS_DE_EXEMPLO.forEach { servico ->
                database.execSQL(
                    "INSERT INTO `Servico` (`nome`, `descricao`, `horario`) VALUES (?, ?, ?)",
                    arrayOf(servico.nome, servico.descricao, servico.horario)
                )
            }
        }

        // Cria uma única instância do banco para reutilização no aplicativo.
        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ubs_database"
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .addCallback(DATABASE_CALLBACK)
                    .build().also { instance = it }
            }
        }
    }
}
