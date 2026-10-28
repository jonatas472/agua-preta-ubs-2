package br.edu.ifpe.ubsaude

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Fronteira de Arquivo: Banco local Room usado pela funcionalidade de Cadastro.
 */
@Database(entities = [Cadastro::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // Disponibiliza o DAO usado para ler e gravar cadastros.
    abstract fun cadastroDao(): CadastroDao

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
                ).build().also { instance = it }
            }
        }
    }
}
