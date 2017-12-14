class Pencil {

    private sharpness

    Pencil(sharpness = 100) {
        this.sharpness = sharpness
    }

    def write(Paper paper, String text) {
        def charactersToBeAdded = text.toCharArray().collect { checkAndUpdateSharpness(it) ? it : ' ' }
        paper.contents += charactersToBeAdded.join()
    }

    private checkAndUpdateSharpness(Character letter) {
        def initialSharpness = this.sharpness
        this.sharpness -= sharpnessConsumedBy(letter)
        initialSharpness > 0
    }

    private static sharpnessConsumedBy(Character letter) {
        if (letter.isUpperCase()) return 2
        if (letter.isLowerCase()) return 1
        return 0
    }

}
