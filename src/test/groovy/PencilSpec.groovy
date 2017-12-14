import spock.lang.Specification

class PencilSpec extends Specification {

    Paper paper
    Pencil pencil

    def setup() {
        paper = new Paper()
    }

    def 'it can write on blank paper'() {
        given:
        def pencil = new Pencil()

        when:
        pencil.write(paper, 'my first sentence')

        then:
        paper.contents == 'my first sentence'
    }

    def 'it can write on paper that already has text'() {
        given:
        def pencil = new Pencil()
        paper.contents = 'prior text'

        when:
        pencil.write(paper, ' more text')

        then:
        paper.contents == 'prior text more text'
    }

    def 'given an initial sharpness, it will write that many lower case letters then blanks'() {
        given:
        def pencil = new Pencil(5)

        when:
        pencil.write(paper, 'e'*6)

        then:
        paper.contents == 'eeeee' + ' '*1
    }

    def 'upper case letters degrade sharpness by two'() {
        given:
        def pencil = new Pencil(6)

        when:
        pencil.write(paper, 'E'*6)

        then:
        paper.contents == 'EEE' + ' '*3
    }

    def 'spaces do not consume sharpness'() {
        given:
        def pencil = new Pencil(3)

        when:
        pencil.write(paper, 'a a a')

        then:
        paper.contents == 'a a a'
    }

}
