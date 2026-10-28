package br.edu.ifpe.ubsaude

import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Fronteira de Arquivo: Integração entre as ações de Cadastro e a camada Room.
 */
class CadastroViewModel(
    private val cadastroDao: CadastroDao
) : ViewModel() {
    val cadastros: Flow<List<Cadastro>> = cadastroDao.listar()

    private val _mensagem = MutableStateFlow<String?>(null)
    val mensagem: StateFlow<String?> = _mensagem.asStateFlow()

    // Salva o cadastro em uma coroutine e publica o resultado para a interface.
    fun salvar(cadastro: Cadastro) {
        viewModelScope.launch {
            _mensagem.value = null
            try {
                cadastroDao.inserir(cadastro)
                _mensagem.value = "Cadastro salvo com sucesso."
            } catch (exception: SQLiteException) {
                Log.e(TAG, "Não foi possível salvar o cadastro no banco local.", exception)
                _mensagem.value = "Não foi possível salvar. Tente novamente."
            }
        }
    }

    private companion object {
        const val TAG = "CadastroViewModel"
    }
}
