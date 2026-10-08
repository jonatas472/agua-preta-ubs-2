package br.edu.ifpe.ubsaude

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.ifpe.ubsaude.ui.theme.GreenLight
import br.edu.ifpe.ubsaude.ui.theme.GreenPrimary

/**
 * Telas do Protótipo UBS+ v2
 * Adaptado para a nova identidade visual do Grupo Apollo.
 * Foco em simplicidade para estudantes do 3º Ano.
 */

// --- TELA PRINCIPAL (HOME) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, onOpenDrawer: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(stringResource(R.string.header_unit), fontSize = 11.sp, color = Color.Gray)
                        Image(
                            painter = painterResource(id = R.drawable.logo_ubs),
                            contentDescription = "Logo UBS+",
                            modifier = Modifier.height(28.dp)
                        )
                        Text(stringResource(R.string.header_location), fontSize = 10.sp, color = Color.Gray)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Notificações */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notificações")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Card de Próximo Agendamento com "efeito cascata" simples
            NextAppointmentCard()

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.menu_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            // Botão Principal: Agendar
            ActionButtonBig(
                text = stringResource(R.string.btn_schedule),
                icon = Icons.Default.DateRange,
                onClick = { navController.navigate("agendamento") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ActionButtonSmall(
                    text = stringResource(R.string.btn_services),
                    icon = Icons.AutoMirrored.Filled.List,
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("servicos") }
                )
                ActionButtonSmall(
                    text = stringResource(R.string.btn_accessibility),
                    icon = Icons.Default.Settings,
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("acessibilidade") }
                )
            }
        }
    }
}

@Composable
fun NextAppointmentCard() {
    // Efeito de camadas usando um Box e um card menor atrás
    Box(contentAlignment = Alignment.BottomCenter) {
        // Camada de fundo (cascata)
        Surface(
            modifier = Modifier.fillMaxWidth(0.9f).height(100.dp),
            color = GreenPrimary.copy(alpha = 0.3f),
            shape = RoundedCornerShape(16.dp)
        ) {}
        
        // Card Principal
        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = GreenPrimary)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        stringResource(R.string.next_schedule),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.White)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text("Paciente: João da Silva", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Registro: 12/10/2026", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Sem horário agendado", color = Color.White, modifier = Modifier.weight(1f))
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun ActionButtonBig(text: String, icon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(80.dp),
        colors = ButtonDefaults.buttonColors(containerColor = GreenLight, contentColor = Color.Black),
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(32.dp), tint = GreenPrimary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Composable
fun ActionButtonSmall(text: String, icon: ImageVector, modifier: Modifier, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        color = GreenLight,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Icon(icon, contentDescription = null, tint = GreenPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text, fontWeight = FontWeight.Bold, fontSize = 14.sp, lineHeight = 16.sp)
        }
    }
}

// --- TELA DE AGENDAMENTO ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var sus by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var servico by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.btn_schedule), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(stringResource(R.string.schedule_description), color = Color.Gray)

            OutlinedTextField(
                value = nome, onValueChange = { nome = it },
                label = { Text(stringResource(R.string.label_name)) },
                placeholder = { Text("Ex: João da Silva") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
            )
            
            OutlinedTextField(
                value = sus, onValueChange = { sus = it },
                label = { Text(stringResource(R.string.label_sus)) },
                placeholder = { Text("000 0000 0000 0000") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = { Icon(Icons.Default.AccountBox, contentDescription = null) }
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = data, onValueChange = { data = it },
                    label = { Text(stringResource(R.string.label_date)) },
                    placeholder = { Text("DD/MM/AA") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) }
                )
                OutlinedTextField(
                    value = hora, onValueChange = { hora = it },
                    label = { Text(stringResource(R.string.label_time)) },
                    placeholder = { Text("00:00") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon = { Icon(Icons.Default.Notifications, contentDescription = null) }
                )
            }

            OutlinedTextField(
                value = servico, onValueChange = { servico = it },
                label = { Text(stringResource(R.string.label_service)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("confirmacao") },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(stringResource(R.string.btn_confirm_schedule), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// --- TELA DE CONFIRMAÇÃO ---
@Composable
fun ConfirmationScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(100.dp).background(GreenPrimary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(60.dp))
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(stringResource(R.string.title_confirmed), fontSize = 24.sp, fontWeight = FontWeight.Bold, color = GreenPrimary)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            stringResource(R.string.text_confirmed),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = GreenLight),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(stringResource(R.string.card_summary), fontWeight = FontWeight.Bold)
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color.LightGray)
                Text("Serviço: Clínico Geral")
                Text("Data: 20/10/2026")
                Text("Horário: 09:00")
            }
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(stringResource(R.string.btn_back_home), fontWeight = FontWeight.Bold)
        }
    }
}

// --- TELA DE SERVIÇOS ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreen(navController: NavController) {
    val servicos = listOf(
        Triple("Clínico Geral", "Segunda a Sexta", "08:00 às 17:00"),
        Triple("Odontologia", "Terça e Quinta", "09:00 às 16:00"),
        Triple("Vacinação", "Segunda a Sexta", "08:00 às 17:00"),
        Triple("Pré-Natal", "Quarta-feira", "08:00 às 12:00")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.services_title), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(servicos) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = CardDefaults.outlinedCardBorder(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    ListItem(
                        headlineContent = { Text(item.first, fontWeight = FontWeight.Bold) },
                        supportingContent = { Text("${item.second}\n${item.third}") },
                        leadingContent = { 
                            Box(Modifier.size(40.dp).background(GreenLight, CircleShape), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Info, contentDescription = null, tint = GreenPrimary)
                            }
                        }
                    )
                }
            }
        }
    }
}

// --- TELA MEUS AGENDAMENTOS ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppointmentsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.my_schedules_title), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("João da Silva", fontWeight = FontWeight.Bold)
                    Text("Cartão SUS: 123 4567 8901 2345", fontSize = 12.sp, color = Color.Gray)
                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
                    Text("Serviço: Clínico Geral", fontWeight = FontWeight.Medium)
                    Text("Data: 20/10/2026 - 09:00")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Ver detalhes", color = GreenPrimary, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.End))
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = { navController.navigate("agendamento") },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(stringResource(R.string.btn_schedule), fontWeight = FontWeight.Bold)
            }
        }
    }
}

// --- TELA DE PERFIL ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.btn_profile), fontWeight = FontWeight.Bold) })
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(120.dp).background(GreenLight, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(80.dp), tint = GreenPrimary)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text("João da Silva", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("Cartão SUS: 123 4567 8901 2345", color = Color.Gray)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Último agendamento", fontWeight = FontWeight.Bold, color = GreenPrimary)
                    Text("Clínico Geral - 10/09/2026")
                }
            }
        }
    }
}

// --- TELA DE ACESSIBILIDADE ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccessibilityScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.btn_accessibility), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AccessibilityOption(text = "Texto ampliado", icon = Icons.Default.Edit)
            AccessibilityOption(text = "Alto contraste", icon = Icons.Default.Star)
        }
    }
}

@Composable
fun AccessibilityOption(text: String, icon: ImageVector) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(70.dp),
        color = GreenLight,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = GreenPrimary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Switch(checked = false, onCheckedChange = {})
        }
    }
}

// --- COMPONENTES DE NAVEGAÇÃO ---

@Composable
fun UBSBottomNavigation(navController: NavController, currentRoute: String?) {
    NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
        val items = listOf(
            Triple("home", stringResource(R.string.nav_home), Icons.Default.Home),
            Triple("meus_agendamentos", stringResource(R.string.nav_schedules), Icons.Default.DateRange),
            Triple("servicos", stringResource(R.string.nav_services), Icons.Default.Info),
            Triple("perfil", stringResource(R.string.nav_profile), Icons.Default.Person)
        )
        
        items.forEach { (route, label, icon) ->
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, fontSize = 10.sp) },
                selected = currentRoute == route,
                onClick = {
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            popUpTo("home") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = GreenPrimary,
                    selectedTextColor = GreenPrimary,
                    indicatorColor = GreenLight
                )
            )
        }
    }
}

@Composable
fun UBSDrawerContent(navController: NavController, onClose: () -> Unit) {
    ModalDrawerSheet(drawerContainerColor = Color.White) {
        Box(
            modifier = Modifier.fillMaxWidth().background(GreenPrimary).padding(24.dp)
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.logo_ubs),
                    contentDescription = null,
                    modifier = Modifier.height(40.dp).clip(RoundedCornerShape(4.dp)).background(Color.White.copy(alpha = 0.9f))
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text("Menu UBS+", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        DrawerItem("Agendar Consulta", Icons.Default.AddCircle) { navController.navigate("agendamento"); onClose() }
        DrawerItem("Meus Agendamentos", Icons.Default.DateRange) { navController.navigate("meus_agendamentos"); onClose() }
        DrawerItem("Serviços e Horários", Icons.AutoMirrored.Filled.List) { navController.navigate("servicos"); onClose() }
        DrawerItem("Acessibilidade", Icons.Default.Settings) { navController.navigate("acessibilidade"); onClose() }
        DrawerItem("Perfil", Icons.Default.Person) { navController.navigate("perfil"); onClose() }
        
        Spacer(modifier = Modifier.weight(1f))
        
        Text(
            "Versão do Protótipo 2.0",
            modifier = Modifier.padding(16.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun DrawerItem(label: String, icon: ImageVector, onClick: () -> Unit) {
    NavigationDrawerItem(
        label = { Text(label, fontWeight = FontWeight.Medium) },
        selected = false,
        onClick = onClick,
        icon = { Icon(icon, contentDescription = null, tint = GreenPrimary) },
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
        colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
    )
}

// --- TELA DE VACINAS (MANTIDA DO PROTÓTIPO ANTERIOR) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VaccinesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.vaccines_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Mural de Vacinas (Desativado no novo design)", color = Color.Gray)
        }
    }
}
