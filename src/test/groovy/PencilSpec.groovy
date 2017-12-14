import spock.lang.Specification

class PencilSpec extends Specification {

    Paper paper
    Pencil pencil

    def setup() {
        paper = new Paper()
        pencil = new Pencil()
    }

    def 'it can write on blank paper'() {
        when:
        pencil.write(paper, 'my first sentence')

        then:
        paper.contents == 'my first sentence'
    }

    def 'it can write on paper that already has text'() {
        given:
        paper.contents = 'prior text'

        when:
        pencil.write(paper, ' more text')

        then:
        paper.contents == 'prior text more text'
    }

}
