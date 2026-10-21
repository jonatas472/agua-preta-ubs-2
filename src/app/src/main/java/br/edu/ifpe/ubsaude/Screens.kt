package br.edu.ifpe.ubsaude

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
 * Telas do Protótipo UBS+ v2 - Edição Final Strict (Fundo Branco / Letras Pretas)
 * Garantindo consistência absoluta com o design: https://ubs-patient-path.lovable.app
 * 
 * PADRONIZAÇÃO REALIZADA:
 * 1. Todas as telas com fundo Color.White.
 * 2. Todas as letras em Color.Black (inclusive botões).
 * 3. Uso de GreenLight como fundo de botões para manter legibilidade das letras pretas.
 */

// --- TELA PRINCIPAL (HOME) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, onOpenDrawer: () -> Unit) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(stringResource(R.string.header_unit), fontSize = 11.sp, color = Color.Black)
                        Image(
                            painter = painterResource(id = R.drawable.logo_ubs),
                            contentDescription = "Logo UBS+",
                            modifier = Modifier.height(28.dp).background(Color.White)
                        )
                        Text(stringResource(R.string.header_location), fontSize = 10.sp, color = Color.Black)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Notificações */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notificações", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Card de Próximo Agendamento
            NextAppointmentCard()

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.menu_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            // Botão Principal: Agendar (Verde Claro com letras Pretas)
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
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(2.dp, GreenPrimary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(R.string.next_schedule),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Icon(Icons.Default.DateRange, contentDescription = null, tint = GreenPrimary)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("Paciente: João da Silva", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("Registro: 12/10/2026", color = Color.Black, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Sem agendamento marcado", color = Color.Black, fontSize = 14.sp, modifier = Modifier.weight(1f))
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = GreenPrimary)
            }
        }
    }
}

@Composable
fun ActionButtonBig(text: String, icon: ImageVector, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(84.dp),
        color = GreenLight,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, 
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(32.dp), tint = GreenPrimary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1f))
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Black)
        }
    }
}

@Composable
fun ActionButtonSmall(text: String, icon: ImageVector, modifier: Modifier, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(110.dp),
        color = Color.White,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Icon(icon, contentDescription = null, tint = GreenPrimary, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text, fontWeight = FontWeight.Bold, fontSize = 14.sp, lineHeight = 18.sp, color = Color.Black)
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
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.btn_schedule), fontWeight = FontWeight.Bold, color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).background(Color.White).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(stringResource(R.string.schedule_description), color = Color.Black)

            OutlinedTextField(
                value = nome, onValueChange = { nome = it },
                label = { Text(stringResource(R.string.label_name), color = Color.Black) },
                placeholder = { Text("Ex: João da Silva") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = GreenPrimary,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = GreenPrimary) }
            )
            
            OutlinedTextField(
                value = sus, onValueChange = { sus = it },
                label = { Text(stringResource(R.string.label_sus), color = Color.Black) },
                placeholder = { Text("000 0000 0000 0000") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black, 
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                leadingIcon = { Icon(Icons.Default.AccountBox, contentDescription = null, tint = GreenPrimary) }
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = data, onValueChange = { data = it },
                    label = { Text(stringResource(R.string.label_date), color = Color.Black) },
                    placeholder = { Text("DD/MM/AAAA") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black, 
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black
                    ),
                    leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null, tint = GreenPrimary) }
                )
                OutlinedTextField(
                    value = hora, onValueChange = { hora = it },
                    label = { Text(stringResource(R.string.label_time), color = Color.Black) },
                    placeholder = { Text("00:00") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black, 
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black
                    ),
                    leadingIcon = { Icon(Icons.Default.Notifications, contentDescription = null, tint = GreenPrimary) }
                )
            }

            OutlinedTextField(
                value = servico, onValueChange = { servico = it },
                label = { Text(stringResource(R.string.label_service), color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black, 
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                leadingIcon = { Icon(Icons.Default.Info, contentDescription = null, tint = GreenPrimary) }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("confirmacao") },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenLight),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, GreenPrimary)
            ) {
                Text(stringResource(R.string.btn_confirm_schedule), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

// --- TELA DE CONFIRMAÇÃO ---
@Composable
fun ConfirmationScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(90.dp).background(Color.White, CircleShape).border(2.dp, GreenPrimary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Check, contentDescription = null, tint = GreenPrimary, modifier = Modifier.size(54.dp))
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(stringResource(R.string.title_confirmed), fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Text(
            stringResource(R.string.text_confirmed),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 16.sp
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(stringResource(R.string.card_summary), fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 18.sp)
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray)
                Text("Serviço: Clínico Geral", color = Color.Black, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Data: 20/Out/2026", color = Color.Black, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Unidade: UBS Água Preta", color = Color.Black, fontSize = 16.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenLight, contentColor = Color.Black),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, GreenPrimary)
        ) {
            Text(stringResource(R.string.btn_back_home), fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}

// --- TELA DE SERVIÇOS ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreen(navController: NavController) {
    val servicos = listOf(
        Triple("Clínico Geral", "Segunda a Sexta", "08h às 17h"),
        Triple("Odontologia", "Terça e Quinta", "09h às 16h"),
        Triple("Vacinação", "Segunda a Sexta", "08h às 17h"),
        Triple("Pré-Natal", "Quarta-feira", "08h às 12h")
    )

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.services_title), fontWeight = FontWeight.Bold, color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).background(Color.White).padding(16.dp), 
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(servicos) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    ListItem(
                        headlineContent = { Text(item.first, fontWeight = FontWeight.Bold, color = Color.Black) },
                        supportingContent = { Text("${item.second}\n${item.third}", color = Color.Black) },
                        leadingContent = { 
                            Box(
                                Modifier.size(44.dp).background(Color.White, CircleShape).border(1.dp, GreenPrimary, CircleShape), 
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Info, contentDescription = null, tint = GreenPrimary)
                            }
                        },
                        colors = ListItemDefaults.colors(containerColor = Color.White)
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
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.my_schedules_title), fontWeight = FontWeight.Bold, color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).background(Color.White).padding(16.dp)) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("João da Silva", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text("Cartão SUS: 123 4567 8901 2345", fontSize = 12.sp, color = Color.Black)
                    HorizontalDivider(Modifier.padding(vertical = 12.dp), color = Color.LightGray)
                    Text("Clínico Geral", fontWeight = FontWeight.Medium, color = Color.Black, fontSize = 16.sp)
                    Text("Agendado para: 20/10/2026 - 09:00", color = Color.Black, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Ver detalhes", 
                        color = GreenPrimary, 
                        fontWeight = FontWeight.Bold, 
                        modifier = Modifier.align(Alignment.End).clickable { /* Detalhes */ }
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = { navController.navigate("agendamento") },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenLight, contentColor = Color.Black),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, GreenPrimary)
            ) {
                Text(stringResource(R.string.btn_schedule), fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

// --- TELA DE PERFIL ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.btn_profile), fontWeight = FontWeight.Bold, color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).background(Color.White).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(120.dp).background(Color.White, CircleShape).border(2.dp, GreenPrimary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(80.dp), tint = GreenPrimary)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text("João da Silva", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text("Cartão SUS: 123 4567 8901 2345", color = Color.Black, fontSize = 16.sp)
            
            Spacer(modifier = Modifier.height(40.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Último agendamento", fontWeight = FontWeight.Bold, color = GreenPrimary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Clínico Geral - 12/10/2026", color = Color.Black, fontSize = 16.sp)
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
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.btn_accessibility), fontWeight = FontWeight.Bold, color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).background(Color.White).padding(16.dp), 
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AccessibilityOption(text = "Texto ampliado", icon = Icons.Default.Edit)
            AccessibilityOption(text = "Alto contraste", icon = Icons.Default.Star)
        }
    }
}

@Composable
fun AccessibilityOption(text: String, icon: ImageVector) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(72.dp),
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = GreenPrimary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.weight(1f), color = Color.Black)
            Switch(checked = false, onCheckedChange = {}, colors = SwitchDefaults.colors(checkedThumbColor = GreenPrimary))
        }
    }
}

// --- NAVEGAÇÃO ---

@Composable
fun UBSBottomNavigation(navController: NavController, currentRoute: String?) {
    NavigationBar(containerColor = Color.White, tonalElevation = 0.dp) {
        val items = listOf(
            Triple("home", stringResource(R.string.nav_home), Icons.Default.Home),
            Triple("meus_agendamentos", stringResource(R.string.nav_schedules), Icons.Default.DateRange),
            Triple("servicos", stringResource(R.string.nav_services), Icons.Default.Info),
            Triple("perfil", stringResource(R.string.nav_profile), Icons.Default.Person)
        )
        
        items.forEach { (route, label, icon) ->
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, fontSize = 10.sp, color = Color.Black) },
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
                    unselectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedTextColor = Color.Black,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun UBSDrawerContent(navController: NavController, onClose: () -> Unit) {
    ModalDrawerSheet(drawerContainerColor = Color.White) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(24.dp)
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.logo_ubs),
                    contentDescription = "Logo UBS+",
                    modifier = Modifier.height(44.dp).background(Color.White)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Menu UBS+", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                HorizontalDivider(color = GreenPrimary, thickness = 3.dp, modifier = Modifier.fillMaxWidth(0.2f))
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        DrawerItem("Início", Icons.Default.Home) { navController.navigate("home"); onClose() }
        DrawerItem("Agendar Consulta", Icons.Default.AddCircle) { navController.navigate("agendamento"); onClose() }
        DrawerItem("Meus Agendamentos", Icons.Default.DateRange) { navController.navigate("meus_agendamentos"); onClose() }
        DrawerItem("Serviços", Icons.AutoMirrored.Filled.List) { navController.navigate("servicos"); onClose() }
        DrawerItem("Perfil", Icons.Default.Person) { navController.navigate("perfil"); onClose() }
        DrawerItem("Acessibilidade", Icons.Default.Settings) { navController.navigate("acessibilidade"); onClose() }
        
        Spacer(modifier = Modifier.weight(1f))
        
        Text(
            "Protótipo v2.0 - Grupo Apollo",
            modifier = Modifier.padding(24.dp),
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@Composable
fun DrawerItem(label: String, icon: ImageVector, onClick: () -> Unit) {
    NavigationDrawerItem(
        label = { Text(label, fontWeight = FontWeight.Medium, color = Color.Black, fontSize = 16.sp) },
        selected = false,
        onClick = onClick,
        icon = { Icon(icon, contentDescription = null, tint = GreenPrimary) },
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
    )
}
