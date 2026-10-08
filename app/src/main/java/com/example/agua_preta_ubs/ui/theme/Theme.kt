package com.example.agua_preta_ubs.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import br.edu.ifpe.ubsaude.ui.theme.DarkBackground
import br.edu.ifpe.ubsaude.ui.theme.DarkSurface
import br.edu.ifpe.ubsaude.ui.theme.GreenPrimary
import br.edu.ifpe.ubsaude.ui.theme.GreenSecondary
import br.edu.ifpe.ubsaude.ui.theme.GreenTertiary
import br.edu.ifpe.ubsaude.ui.theme.LightBackground
import br.edu.ifpe.ubsaude.ui.theme.LightSurface

private val DarkColorScheme = darkColorScheme(
    primary = GreenPrimary,
    secondary = GreenSecondary,
    tertiary = GreenTertiary,
    background = DarkBackground,
    surface = DarkSurface
)

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    secondary = GreenSecondary,
    tertiary = GreenTertiary,
    background = LightBackground,
    surface = LightSurface
)

@Composable
fun AguaPretaubsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is disabled to match the new visual identity
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
