import spock.lang.Specification

class PencilSpec extends Specification {

    Paper paper

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

    def 'it will write a lower case letter instead of an upper case letter if it only has one sharpness left'() {
        given:
        def pencil = new Pencil(1)

        when:
        pencil.write(paper, 'A')

        then:
        paper.contents == 'a'
    }

    def 'pencil degrades over multiple writings'() {
        given:
        def pencil = new Pencil(3)

        when:
        pencil.write(paper, 'a'*4)
        pencil.write(paper, 'a'*4)

        then:
        paper.contents == 'a'*3 + ' '*5
    }

    def 'sharpening a pencil restores its original sharpness'() {
        given:
        def pencil = new Pencil(2)

        when:
        pencil.write(paper, 'q'*3)
        pencil.sharpen()
        pencil.write(paper, 'q'*3)

        then:
        paper.contents == 'qq qq '
    }

    def 'can be sharpened a finite number of times'() {
        given:
        def pencil = new Pencil(5, 1)

        when:
        pencil.write(paper, 'q'*6)
        pencil.sharpen()
        pencil.write(paper, 'q'*6)
        pencil.sharpen()
        pencil.write(paper, 'q'*6)

        then:
        paper.contents == 'qqqqq '*2 + ' '*6
    }

}
