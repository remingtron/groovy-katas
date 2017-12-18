class Pencil {

    private sharpness, initialSharpness, length, eraserDurability

    Pencil(sharpness = 100, length = 10, eraserDurability = 100) {
        this.initialSharpness = sharpness
        this.sharpness = sharpness
        this.length = length
        this.eraserDurability = eraserDurability
    }

    def write(Paper paper, String text) {
        def charactersToBeAdded = text.toCharArray().collect { attemptToWriteLetter(it) }
        paper.contents += charactersToBeAdded.join()
    }

    def sharpen() {
        if (this.length > 0) {
            this.sharpness = initialSharpness
            length--
        }
    }

    def erase(Paper paper, String toRemove) {
        def indexOfLastOccurrence = paper.contents.lastIndexOf(toRemove)
        def contentsArray = paper.contents.toCharArray()

        if (indexOfLastOccurrence == -1) {
            return
        }

        (indexOfLastOccurrence..<indexOfLastOccurrence+toRemove.length()).step(-1).each { index ->
            if (this.eraserDurability > 0 && contentsArray[index] != ' ') {
                contentsArray[index] = ' '
                this.eraserDurability--
            }
        }
        paper.contents = contentsArray.join()
    }

    private attemptToWriteLetter(Character letter) {
        def sharpnessRequired = sharpnessConsumedBy(letter)
        if (this.sharpness >= sharpnessRequired) {
            this.sharpness -= sharpnessRequired
            return letter
        }
        return letter.isUpperCase() ? attemptToWriteLetter(letter.toLowerCase()) : ' '
    }

    private static sharpnessConsumedBy(Character letter) {
        if (letter.isUpperCase()) return 2
        if (letter.isLowerCase()) return 1
        return 0
    }
}
