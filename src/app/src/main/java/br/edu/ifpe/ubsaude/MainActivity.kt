package br.edu.ifpe.ubsaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
        val database = AppDatabase.getDatabase(this)
        val repository = AgendamentoRepository(database.agendamentoDao())
        val factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(AgendamentoViewModel::class.java)) {
                    return AgendamentoViewModel(repository) as T
                }
                throw IllegalArgumentException("ViewModel não reconhecido: ${modelClass.name}")
            }
        }
        val agendamentoViewModel = ViewModelProvider(this, factory)[AgendamentoViewModel::class.java]
        setContent {
            UBSTheme {
                UBSAppContent(agendamentoViewModel)
            }
        }
    }
}

@Composable
fun UBSAppContent(agendamentoViewModel: AgendamentoViewModel) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var enlargedText by rememberSaveable { mutableStateOf(false) }
    var highContrast by rememberSaveable { mutableStateOf(false) }
    val density = LocalDensity.current

    CompositionLocalProvider(
        LocalAccessibilityPreferences provides AccessibilityPreferences(enlargedText, highContrast),
        LocalDensity provides androidx.compose.ui.unit.Density(
            density.density,
            density.fontScale * if (enlargedText) 1.2f else 1f
        )
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                UBSDrawerContent(
                    navController = navController,
                    onClose = { scope.launch { drawerState.close() } }
                )
            }
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.White,
                bottomBar = {
                    if (currentRoute != "confirmacao") UBSBottomNavigation(navController, currentRoute)
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("home") {
                        HomeScreen(navController, { scope.launch { drawerState.open() } }, agendamentoViewModel)
                    }
                    composable("agendamento") { ScheduleScreen(navController, agendamentoViewModel) }
                    composable("confirmacao") { ConfirmationScreen(navController, agendamentoViewModel) }
                    composable("servicos") { ServicesScreen(navController) }
                    composable("meus_agendamentos") { MyAppointmentsScreen(navController, agendamentoViewModel) }
                    composable("perfil") { ProfileScreen(navController, agendamentoViewModel) }
                    composable("acessibilidade") {
                        AccessibilityScreen(
                            navController,
                            enlargedText,
                            { enlargedText = it },
                            highContrast,
                            { highContrast = it }
                        )
                    }
                }
            }
        }
    }
}
