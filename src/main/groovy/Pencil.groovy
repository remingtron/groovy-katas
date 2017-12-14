class Pencil {

    private sharpness

    Pencil(sharpness = 100) {
        this.sharpness = sharpness
    }

    def write(Paper paper, String text) {
        def charactersToBeAdded = text.toCharArray().collect { attemptToWriteLetter(it) }
        paper.contents += charactersToBeAdded.join()
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
