package me.tomassetti.examples.MarkupParser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.io.StringWriter;

public class MarkupErrorListener extends BaseErrorListener {
    private String _symbol = "";
    private StringWriter _stream;

    public MarkupErrorListener(StringWriter stream) {
        this._stream = stream;
    }

    public String getSymbol() {
        return _symbol;
    }

    public StringWriter getStream() {
        return _stream;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        this._stream.write(msg);
        this._stream.write(System.getProperty("line.separator"));

        if (offendingSymbol.getClass().getName() == "org.antlr.v4.runtime.CommonToken") {
            CommonToken token = (CommonToken) offendingSymbol;
            this._symbol = token.getText();
        }
    }

    ;
}