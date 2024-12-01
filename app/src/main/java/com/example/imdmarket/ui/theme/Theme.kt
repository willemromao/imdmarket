package com.example.imdmarket.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color

// Definição de cores para o tema
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0F3C8D),
    secondary = Color(0xFF3F51B5)
)

@Composable
fun IMDMarketTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IMDMarketTheme {
        // Exemplo de tela para visualização
    }
}
