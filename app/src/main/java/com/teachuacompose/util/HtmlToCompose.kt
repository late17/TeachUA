package com.teachuacompose.util


import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.teachuacompose.ui.theme.Typography
import kotlin.math.min


/**
 * The tags to interpret. Add tags here and in [tagToStyle].
 */
private val tags = linkedMapOf(
    "<b>" to "</b>",
    "<i>" to "</i>",
    "<u>" to "</u>",
    "<h1>" to "</h1>",
    "<h3>" to "</h3>",
    "<em>" to "</em>",
    "<strong>" to "</strong>"
)


private val placeEnterTag = listOf("</p>", "<br>")

/**
 * The main entry point. Call this on a String and use the result in a Text.
 */
fun String.parseHtml(): AnnotatedString {

    //delete <p> tag but puts \n instead of </p> in the next cycle
    var newlineReplace = this.replace("<p>", "")
    for(tag in placeEnterTag) {
        newlineReplace = newlineReplace.replace(tag, "\n")
    }


    return buildAnnotatedString {
        recurse(newlineReplace, this)
    }
}

/**
 * Recurses through the given HTML String to convert it to an AnnotatedString.
 *
 * @param string the String to examine.
 * @param to the AnnotatedString to append to.
 */
private fun recurse(string: String, to: AnnotatedString.Builder) {
    //Find the opening tag that the given String starts with, if any.
    val startTag = tags.keys.find { string.startsWith(it) }

    //Find the closing tag that the given String starts with, if any.
    val endTag = tags.values.find { string.startsWith(it) }

    when {
        //If the String starts with a closing tag, then pop the latest-applied
        //SpanStyle and continue recursing.
        tags.any { string.startsWith(it.value) } -> {
            to.pop()
            recurse(string.removeRange(0, endTag!!.length), to)
        }
        //If the String starts with an opening tag, apply the appropriate
        //SpanStyle and continue recursing.
        tags.any { string.startsWith(it.key) } -> {
            to.pushStyle(tagToStyle(startTag!!))
            recurse(string.removeRange(0, startTag.length), to)
        }
        //If the String doesn't start with an opening or closing tag, but does contain either,
        //find the lowest index (that isn't -1/not found) for either an opening or closing tag.
        //Append the text normally up until that lowest index, and then recurse starting from that index.
        tags.any { string.contains(it.key) || string.contains(it.value) } -> {
            val firstStart = tags.keys.map { string.indexOf(it) }.filterNot { it == -1 }.minOrNull() ?: -1
            val firstEnd = tags.values.map { string.indexOf(it) }.filterNot { it == -1 }.minOrNull() ?: -1
            val first = when {
                firstStart == -1 -> firstEnd
                firstEnd == -1 -> firstStart
                else -> min(firstStart, firstEnd)
            }

            to.append(string.substring(0, first))

            recurse(string.removeRange(0, first), to)
        }
        //There weren't any supported tags found in the text. Just append it all normally.
        else -> {
            to.append(string)
        }
    }
}

/**
 * Perform harder operations with tags. Like taking href from <a tag
 * in progress
 */
fun function(tag: String?, string: String, to: AnnotatedString.Builder) {
    when (tag) {
        "<a" -> {
            var index = string.indexOf("\"")
            val str = string.removeRange(0, index+1)
            index = str.indexOf("\"")
            val href = str.substring(0, index)
            index = str.indexOf(">")
            val end = str.indexOf("</a>")
            to.addStringAnnotation(tag = "URL", annotation = href, start =  index, end = end)
        }
    }
}



/**
 * Get a [SpanStyle] for a given (opening) tag.
 * Add your own tag styling here by adding its opening tag to
 * the when clause and then instantiating the appropriate [SpanStyle].
 *
 * @return a [SpanStyle] for the given tag.
 */
private fun tagToStyle(tag: String): SpanStyle {
    return when (tag) {
        "<b>", "<strong>" -> {
            SpanStyle(fontWeight = FontWeight.Bold)
        }

        "<i>", "<em>" -> {
            SpanStyle(fontStyle = FontStyle.Italic)
        }
        "<u>" -> {
            SpanStyle(textDecoration = TextDecoration.Underline)
        }
        "<h1>" -> {
            SpanStyle(
                fontSize = Typography.h4.fontSize,
                letterSpacing = Typography.h4.letterSpacing
            )
        }
        "<h3>" -> {
            SpanStyle(
                fontSize = Typography.h5.fontSize,
            )
        }

        //This should only throw if you add a tag to the [tags] Map and forget to add it
        //to this function.
        else -> SpanStyle()
        //else -> throw IllegalArgumentException("Tag $tag is not valid.")
    }
}