package com.example.a25_10_ynov_android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.a25_10_ynov_android.R
import com.example.a25_10_ynov_android.model.DescriptionBean
import com.example.a25_10_ynov_android.model.TempBean
import com.example.a25_10_ynov_android.model.WeatherBean
import com.example.a25_10_ynov_android.model.WindBean
import com.example.a25_10_ynov_android.ui.theme.A25_10_ynov_androidTheme

@Preview(showBackground = true, showSystemUi = true)
@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
            or android.content.res.Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun DetailScreenPreview() {
    A25_10_ynov_androidTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DetailScreen(
                modifier = Modifier.padding(innerPadding),
                //jeu de donnée pour la Preview
                data = WeatherBean(
                    id = 2,
                    name = "Toulouse",
                    main = TempBean(temp = 22.3),
                    weather = listOf(
                        DescriptionBean(description = "partiellement nuageux", icon = "https://picsum.photos/201")
                    ),
                    wind = WindBean(speed = 3.2)
                )
            )
        }
    }
}

@Composable //id du WeatherBean à afficher
fun DetailScreen(
    modifier: Modifier = Modifier,
    data: WeatherBean,
    navHostController: NavHostController? = null
) {

    Column(modifier = modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = data.name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        AsyncImage(
            model = data.weather.firstOrNull()?.icon ?: "",
            //Pour aller le chercher dans string.xml R de votre package com.nom.projet
            //contentDescription = getString(R.string.picture_of_cat),
            //En dur
            contentDescription = "une photo de chat",
            contentScale = ContentScale.FillWidth,

            //Pour toto.png. Si besoin de choisir l'import pour la classe R, c'est celle de votre package
            //Image d'échec de chargement qui sera utilisé par la preview
            error = painterResource(R.drawable.error),
            //Image d'attente.
            //placeholder = painterResource(R.drawable.toto),

            onError = { println(it) },
            modifier = Modifier.fillMaxWidth().weight(2f)
        )

        Text(
            text =  data.getResume(),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth().weight(1f)

        )

        Button(
            onClick = { navHostController?.popBackStack() },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Retour")
        }
    }

}