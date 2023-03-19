/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ast.impl.javacc;

import net.sourceforge.pmd.lang.ast.FileAnalysisException;
import net.sourceforge.pmd.lang.ast.ParseException;
import net.sourceforge.pmd.lang.ast.Parser;
import net.sourceforge.pmd.lang.ast.RootNode;

/**
 * Base implementation of the {@link Parser} interface for JavaCC language
 * implementations. This wraps a parser generated by JavaCC, it's not meant
 * as a base class for the generated parser.
 *
 * @param <R> Type of the root node of this language
 */
public abstract class JjtreeParserAdapter<R extends RootNode> implements Parser {

    protected JjtreeParserAdapter() {
        // inheritance only
    }

    protected abstract JavaccTokenDocument.TokenDocumentBehavior tokenBehavior();

    @Override
    public final R parse(ParserTask task) throws ParseException {
        try {
            // First read the source file and interpret escapes
            CharStream charStream = CharStream.create(task.getTextDocument(), tokenBehavior());
            // We replace the text document, so that it reflects escapes properly
            // Escapes are processed by CharStream#create
            task = task.withTextDocument(charStream.getTokenDocument().getTextDocument());
            // Finally, do the parsing
            return parseImpl(charStream, task);
        } catch (FileAnalysisException tme) {
            throw tme.setFileName(task.getTextDocument().getPathId());
        }
    }

    protected abstract R parseImpl(CharStream cs, ParserTask task) throws ParseException;


    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
