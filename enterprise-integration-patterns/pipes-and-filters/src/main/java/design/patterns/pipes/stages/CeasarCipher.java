package design.patterns.pipes.stages;

import design.patterns.pipes.ProcessingException;
import design.patterns.pipes.models.DataContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating ROT13 ceasar cipher
 */
public class CeasarCipher implements Stage {
    private int charGap;

    public CeasarCipher(int characterGap){
        charGap = characterGap;
    }

    @Override public DataContext execute(DataContext context) throws ProcessingException {
        if(context!=null){
            List<String> encodedSentences = new ArrayList<>();
            if( context.getSentences() != null && !context.getSentences().isEmpty() ){
                for (String sentence : context.getSentences()) {
                    encodedSentences.add( encode(sentence, charGap) );
                }
                context.setSentences(encodedSentences);
            }
        }
        return context;
    }

    private String encode(String sentence, int charGap) {
        charGap = charGap % 26 + 26;
        StringBuilder builder = new StringBuilder();
        for (char i : sentence.toCharArray()) {
            if(Character.isLetter(i)){
                if(Character.isUpperCase(i)){
                    builder.append((char)('A' + (i - 'A' + charGap ) % 26));
                } else {
                    builder.append((char)('a' + (i - 'a' + charGap ) % 26));
                }
            } else {
                builder.append(i);
            }
        }
        return builder.toString();
    }
}
