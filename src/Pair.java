public class Pair {

    private char letter;
    private int frequency;

    //базовый конструктор
    public Pair (char letter){
        this.letter = letter;
        this.frequency = 0;
    }
    //сеттеры-геттеры
    public void setLetter (char letter) throws IllegalArgumentException{
        if ((letter >= 65) && (letter <= 90)){this.letter = letter;}
        else if ((letter >= 97) && (letter <= 122)){this.letter = (char) (letter - 32);}
        else {throw new IllegalArgumentException("not a letter");}
    }
    public void setFrequency (int frequency) throws IllegalArgumentException {
        if (frequency < 0) {throw new IllegalArgumentException("frequency cannot be negative");}
        this.frequency = frequency;
    }
    public char getLetter () {return this.letter;}
    public int getFrequency () {return this.frequency;}

    //+1 появление
    public void addFrequency() {this.frequency++;}

    //перегрузка toString для вывода через print()
    @Override
    public String toString(){
        return (letter + ":" + frequency);
    }
}
