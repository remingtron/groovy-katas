class Pencil {

    private sharpness, initialSharpness, length

    Pencil(sharpness = 100, length = 10) {
        this.initialSharpness = sharpness
        this.sharpness = sharpness
        this.length = length
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
