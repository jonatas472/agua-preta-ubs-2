package br.edu.ifpe.ubsaude

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Fronteira de Arquivo: Banco local Room usado pela funcionalidade de Cadastro.
 */
@Database(entities = [Cadastro::class, Agendamento::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // Disponibiliza o DAO usado para ler e gravar cadastros.
    abstract fun cadastroDao(): CadastroDao
    abstract fun agendamentoDao(): AgendamentoDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        // Cria uma única instância do banco para reutilização no aplicativo.
        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ubs_database"
                ).addMigrations(MIGRATION_1_2).build().also { instance = it }
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """CREATE TABLE IF NOT EXISTS `Agendamento` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `nome` TEXT NOT NULL,
                        `data` TEXT NOT NULL,
                        `cartaoSUS` TEXT NOT NULL,
                        `horario` TEXT NOT NULL,
                        `servico` TEXT NOT NULL
                    )""".trimIndent()
                )
            }
        }
    }
}
