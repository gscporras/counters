package com.counter.android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.counter.android.R

val poppins = FontFamily(
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
)

// set of dark material typography styles to start with.
val DarkTypography = Typography(
    h1 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 36.sp
    ),
    h2 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 22.sp
    ),
    h4 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 12.sp
    ),
    caption = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 10.sp
    ),
    button = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        fontSize = 14.sp
    )
)

// set of light material typography styles to start with.
val LightTypography = Typography(
    h1 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 36.sp
    ),
    h2 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 22.sp
    ),
    h4 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 12.sp
    ),
    caption = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 10.sp
    ),
    button = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Medium,
        color = Color.Black,
        fontSize = 14.sp
    )
)