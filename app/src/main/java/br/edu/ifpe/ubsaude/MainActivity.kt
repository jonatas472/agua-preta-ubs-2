package br.edu.ifpe.ubsaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.edu.ifpe.ubsaude.ui.theme.UBSTheme
import kotlinx.coroutines.launch

/**
 * Ponto de entrada do UBS+ Protótipo V2.
 * Configurado para Fundo Branco e Alto Contraste.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UBSTheme {
                UBSAppContent()
            }
        }
    }
}

@Composable
fun UBSAppContent() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            UBSDrawerContent(
                navController = navController,
                onClose = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        // Scaffold configurado com fundo branco absoluto
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.White,
            bottomBar = {
                if (currentRoute != "confirmacao") {
                    UBSBottomNavigation(navController, currentRoute)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") { 
                    HomeScreen(navController, onOpenDrawer = { scope.launch { drawerState.open() } }) 
                }
                composable("agendamento") { 
                    ScheduleScreen(navController) 
                }
                composable("confirmacao") { 
                    ConfirmationScreen(navController) 
                }
                composable("servicos") { 
                    ServicesScreen(navController) 
                }
                composable("meus_agendamentos") { 
                    MyAppointmentsScreen(navController) 
                }
                composable("perfil") { 
                    ProfileScreen(navController) 
                }
                composable("acessibilidade") { 
                    AccessibilityScreen(navController) 
                }
            }
        }
    }
}
