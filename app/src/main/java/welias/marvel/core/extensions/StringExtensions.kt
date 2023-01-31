package welias.marvel.core.extensions

fun String.checkImageAvailable(): String? {
    return if (this.contains("image_not_available")) {
        this.replace("image_not_available", "") // Change the url to throw a exception
    } else {
        this
    }
}
