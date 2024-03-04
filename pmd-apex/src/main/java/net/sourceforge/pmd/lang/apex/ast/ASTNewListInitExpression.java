/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.ast;

import com.google.summit.ast.initializer.Initializer;

public final class ASTNewListInitExpression extends AbstractApexNode.Single<Initializer> {

    ASTNewListInitExpression(Initializer initializer) {
        super(initializer);
    }


    @Override
    protected <P, R> R acceptApexVisitor(ApexVisitor<? super P, ? extends R> visitor, P data) {
        return visitor.visit(this, data);
    }
}
