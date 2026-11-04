package br.edu.ifpe.ubsaude

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import br.edu.ifpe.ubsaude.ui.theme.GreenLight
import br.edu.ifpe.ubsaude.ui.theme.GreenPrimary

data class AccessibilityPreferences(
    val enlargedText: Boolean = false,
    val highContrast: Boolean = false
)

val LocalAccessibilityPreferences = staticCompositionLocalOf { AccessibilityPreferences() }

/** Usa preto nos acentos para reforçar contraste quando essa opção está ativa. */
@Composable
fun accessiblePrimaryColor(): Color =
    if (LocalAccessibilityPreferences.current.highContrast) Color.Black else GreenPrimary

/** Mantém os contornos visíveis em cartões e controles no modo de alto contraste. */
@Composable
fun accessibleBorderColor(): Color =
    if (LocalAccessibilityPreferences.current.highContrast) Color.Black else Color.LightGray

/** Remove o preenchimento verde dos botões para preservar contraste preto no modo acessível. */
@Composable
fun accessibleButtonColor(): Color =
    if (LocalAccessibilityPreferences.current.highContrast) Color.White else GreenLight