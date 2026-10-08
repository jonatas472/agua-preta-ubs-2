package br.edu.ifpe.ubsaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.ifpe.ubsaude.ui.theme.UBSTheme

/**
 * Fronteira de Arquivo: Ponto de Entrada e Navegação do UBS+
 * Configurado para o Protótipo Inicial.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UBSTheme {
                // Surface é o fundo básico que usa a cor do tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UBSAppNavigation()
                }
            }
        }
    }
}

/**
 * Componente que gerencia as trocas de tela (Navegação)
 */
@Composable
fun UBSAppNavigation() {
    val navController = rememberNavController()

    // O NavHost define o "mapa" do aplicativo
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { 
            HomeScreen(navController) 
        }
        composable("agendamento") { 
            ScheduleScreen(navController) 
        }
        composable("servicos") { 
            ServicesScreen(navController) 
        }
        composable("vacinas") { 
            VaccinesScreen(navController) 
        }
        composable("meus_agendamentos") { 
            MyAppointmentsScreen(navController) 
        }
    }
}
