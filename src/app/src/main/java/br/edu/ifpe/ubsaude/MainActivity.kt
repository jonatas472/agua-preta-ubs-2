package br.edu.ifpe.ubsaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
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
        val agendamentoViewModel =
            ViewModelProvider(this)[AgendamentoViewModel::class.java]
        val servicoViewModel =
            ViewModelProvider(this)[ServicoViewModel::class.java]
        setContent {
            UBSTheme {
                UBSAppContent(agendamentoViewModel, servicoViewModel)
            }
        }
    }
}

@Composable
fun UBSAppContent(
    agendamentoViewModel: AgendamentoViewModel,
    servicoViewModel: ServicoViewModel
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val agendamentos by agendamentoViewModel.agendamentos.collectAsState()
    val erroAoCarregar by agendamentoViewModel.erroAoCarregar.collectAsState()
    val carregando by agendamentoViewModel.carregando.collectAsState()
    val salvando by agendamentoViewModel.salvando.collectAsState()
    val ultimoAgendamento by agendamentoViewModel.ultimoAgendamento.collectAsState()
    val servicos by servicoViewModel.servicos.collectAsState()
    val erroAoCarregarServicos by servicoViewModel.erroAoCarregar.collectAsState()
    val carregandoServicos by servicoViewModel.carregando.collectAsState()
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
                    HomeScreen(
                        navController,
                        agendamentos,
                        carregando,
                        erroAoCarregar,
                        onOpenDrawer = { scope.launch { drawerState.open() } }
                    )
                }
                composable("agendamento") { 
                    ScheduleScreen(navController, agendamentoViewModel, salvando)
                }
                composable("confirmacao") { 
                    ConfirmationScreen(navController, ultimoAgendamento)
                }
                composable("servicos") {
                    ServicesScreen(
                        navController,
                        servicos,
                        carregandoServicos,
                        erroAoCarregarServicos
                    )
                }
                composable("meus_agendamentos") { 
                    MyAppointmentsScreen(
                        navController,
                        agendamentos,
                        carregando,
                        erroAoCarregar
                    )
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
