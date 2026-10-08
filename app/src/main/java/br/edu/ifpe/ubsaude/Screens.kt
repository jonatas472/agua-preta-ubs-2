package br.edu.ifpe.ubsaude

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/**
 * Fronteira de Arquivo: Telas do Protótipo UBS+
 * Desenvolvido pelo Grupo Apollo - 3º Ano Médio.
 * Código focado em simplicidade e didática.
 */

// --- TELA PRINCIPAL ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { 
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.app_name), fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF668D3D),
                    titleContentColor = Color.White
                )
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
            Text(
                text = stringResource(R.string.welcome_message),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF668D3D)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Card de Próximo Agendamento
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Próximo Agendamento:", fontWeight = FontWeight.Bold, color = Color(0xFF388E3C))
                    Text(stringResource(R.string.no_schedules), style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(stringResource(R.string.menu_title), fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))

            // Botões do Menu - Grandes e fáceis de clicar
            MenuButton(stringResource(R.string.btn_schedule)) { navController.navigate("agendamento") }
            MenuButton(stringResource(R.string.btn_services)) { navController.navigate("servicos") }
            MenuButton(stringResource(R.string.btn_vaccines)) { navController.navigate("vacinas") }
            MenuButton(stringResource(R.string.btn_my_schedules)) { navController.navigate("meus_agendamentos") }
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
    var mensagemErro by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.btn_schedule)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text(stringResource(R.string.label_name)) }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = sus, onValueChange = { sus = it }, label = { Text(stringResource(R.string.label_sus)) }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = data, onValueChange = { data = it }, label = { Text(stringResource(R.string.label_date)) }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = hora, onValueChange = { hora = it }, label = { Text(stringResource(R.string.label_time)) }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = servico, onValueChange = { servico = it }, label = { Text(stringResource(R.string.label_service)) }, modifier = Modifier.fillMaxWidth())
            
            if (mensagemErro.isNotEmpty()) {
                Text(mensagemErro, color = Color.Red, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { 
                    if (nome.isBlank() || sus.isBlank() || data.isBlank() || hora.isBlank() || servico.isBlank()) {
                        mensagemErro = "Por favor, preencha todos os campos corretamente para realizar o agendamento."
                    } else {
                        mensagemErro = ""
                        // Ação de salvar virá no futuro com Room
                    }
                },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668D3D))
            ) {
                Text(stringResource(R.string.btn_confirm_schedule), fontSize = 18.sp)
            }
        }
    }
}

// --- TELA DE SERVIÇOS ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreen(navController: NavController) {
    val servicos = listOf(
        Pair("Clínico Geral", "Segunda a Sexta — 08:00 às 17:00"),
        Pair("Odontologia", "Terça e Quinta — 09:00 às 16:00"),
        Pair("Vacinação", "Segunda a Sexta — 08:00 às 17:00"),
        Pair("Pré-Natal", "Quarta-feira — 08:00 às 12:00")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.services_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(servicos) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    ListItem(
                        headlineContent = { Text(item.first, fontWeight = FontWeight.Bold) },
                        supportingContent = { Text(item.second) },
                        leadingContent = { Text("🏥", fontSize = 24.sp) }
                    )
                }
            }
        }
    }
}

// --- TELA VERDADE OU MITO ---
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
        Column(
            modifier = Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            VaccineCard(
                titulo = "Vacina da Gripe",
                afirmacao = "A vacina da gripe causa a própria gripe?",
                resultado = "MITO",
                explicacao = "A vacina é feita com vírus mortos (inativados), por isso não tem capacidade de causar a doença em quem a recebe."
            )
            
            VaccineCard(
                titulo = "Reação de Vacinas",
                afirmacao = "Se eu não tiver febre, a vacina não funcionou?",
                resultado = "MITO",
                explicacao = "A febre é apenas uma reação possível do corpo, mas a ausência dela não significa que a vacina não está protegendo você."
            )
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
                title = { Text(stringResource(R.string.my_schedules_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding), 
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.no_schedules), fontSize = 18.sp, color = Color.Gray)
        }
    }
}

// --- COMPONENTES AUXILIARES ---

@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(70.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668D3D)),
        shape = MaterialTheme.shapes.large
    ) {
        Text(text, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun VaccineCard(titulo: String, afirmacao: String, resultado: String, explicacao: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(titulo, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, color = Color(0xFF2E7D32))
            Spacer(modifier = Modifier.height(8.dp))
            Text("Afirmação: ", fontWeight = FontWeight.Bold)
            Text(afirmacao)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text("Resultado: ", fontWeight = FontWeight.Bold)
                Text(
                    text = resultado, 
                    color = if(resultado == "VERDADE") Color(0xFF1976D2) else Color(0xFFD32F2F), 
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Explicação: ", fontWeight = FontWeight.Bold)
            Text(explicacao, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
