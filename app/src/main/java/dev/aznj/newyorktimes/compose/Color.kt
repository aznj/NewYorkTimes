package dev.aznj.newyorktimes.compose

import androidx.compose.ui.graphics.Color

val LighterGrey = Color(0xFFF8F8F8)
val LightGrey = Color(0xFFCCCBCB)
val FogGrey = Color(0xFFE0E0E0)
val StoneGrey = Color(0xFF9b9b9b)
val DustGrey = Color(0xFFEEEEEE)
val TimelineGrey = Color(0xFFE0E0E0)
val Cultured = Color(0xFFEFEFEF)
val PbBlue = Color(0xFF4480AA)
val StatusInProgress = Color(0xFF47B2FF)
val StatusPrinted = Color(0xFF71C99C)
val ReferralCyan = Color(0xFF00e4c3)
val Grey = Color(0xFF666666)
val BabyBlue = Color(0xFFA2BFD5)
val LightBlue = Color(0xFFECF2F6)
val LightDarkerGrey = Color(0xFFEFEFEF)
val Black = Color(0xFF333333)
val Tangerine = Color(0XFFF56701)
val Peach = Color(0XFFFEE8D9)
val CharcoalGrey = Color(0xFF4A4A4A)
val Tan = Color(0xFFFBF4ED)
val FstCyan = Color(0xFF00bfa5)
val FstBackground = Color(0xFFd8d8d8)
val BlushRed = Color(0xFFD9534F)
val VoucherExpiryColor = Color(0x28D9534F)

fun getColor(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}