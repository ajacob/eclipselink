/*******************************************************************************
 * Copyright (c) 1998, 2010 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available athttp://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle
 *
 ******************************************************************************/
package org.eclipse.persistence.utils.jpa.query.parser;

import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("nls")
public final class RangeVariableDeclarationTest extends AbstractJPQLTest
{
	@Override
	boolean isTolerant()
	{
		return true;
	}

	@Test
	public void testBuildExpression_01()
	{
		String query = "SELECT e FROM Employee e";
		JPQLExpression jpqlExpression = JPQLTests.buildQuery(query);

		Expression expression = jpqlExpression.getQueryStatement();
		assertTrue(expression instanceof SelectStatement);
		SelectStatement selectStatement = (SelectStatement) expression;

		// FromClause
		expression = selectStatement.getFromClause();
		assertTrue(expression instanceof FromClause);
		FromClause fromClause = (FromClause) expression;

		// IdentificationVariableDeclaration
		expression = fromClause.getDeclaration();
		assertTrue(expression instanceof IdentificationVariableDeclaration);
		IdentificationVariableDeclaration identificationVariableDeclaration = (IdentificationVariableDeclaration) expression;

		// RangeVariableDeclaration
		expression = identificationVariableDeclaration.getRangeVariableDeclaration();
		assertTrue(expression instanceof RangeVariableDeclaration);
		RangeVariableDeclaration rangeVariableDeclaration = (RangeVariableDeclaration) expression;

		assertFalse(rangeVariableDeclaration.hasAs());
		assertEquals("Employee e", rangeVariableDeclaration.toParsedText());

		// AbstractSchemaName
		expression = rangeVariableDeclaration.getAbstractSchemaName();
		assertTrue(expression instanceof AbstractSchemaName);
		AbstractSchemaName abstractSchemaName = (AbstractSchemaName) expression;

		assertEquals("Employee", abstractSchemaName.getText());

		// IdentificationVariable
		expression = rangeVariableDeclaration.getIdentificationVariable();
		assertTrue(expression instanceof IdentificationVariable);
		IdentificationVariable identificationVariable = (IdentificationVariable) expression;

		assertEquals("e", identificationVariable.getText());
	}

	@Test
	public void testBuildExpression_02()
	{
		String query = "SELECT e FROM Employee AS e";
		JPQLExpression jpqlExpression = JPQLTests.buildQuery(query);

		Expression expression = jpqlExpression.getQueryStatement();
		assertTrue(expression instanceof SelectStatement);
		SelectStatement selectStatement = (SelectStatement) expression;

		// FromClause
		expression = selectStatement.getFromClause();
		assertTrue(expression instanceof FromClause);
		FromClause fromClause = (FromClause) expression;

		// IdentificationVariableDeclaration
		expression = fromClause.getDeclaration();
		assertTrue(expression instanceof IdentificationVariableDeclaration);
		IdentificationVariableDeclaration identificationVariableDeclaration = (IdentificationVariableDeclaration) expression;

		// RangeVariableDeclaration
		expression = identificationVariableDeclaration.getRangeVariableDeclaration();
		assertTrue(expression instanceof RangeVariableDeclaration);
		RangeVariableDeclaration rangeVariableDeclaration = (RangeVariableDeclaration) expression;

		assertTrue(rangeVariableDeclaration.hasAs());
		assertEquals("Employee AS e", rangeVariableDeclaration.toParsedText());

		// AbstractSchemaName
		expression = rangeVariableDeclaration.getAbstractSchemaName();
		assertTrue(expression instanceof AbstractSchemaName);
		AbstractSchemaName abstractSchemaName = (AbstractSchemaName) expression;

		assertEquals("Employee", abstractSchemaName.getText());

		// IdentificationVariable
		expression = rangeVariableDeclaration.getIdentificationVariable();
		assertTrue(expression instanceof IdentificationVariable);
		IdentificationVariable identificationVariable = (IdentificationVariable) expression;

		assertEquals("e", identificationVariable.getText());
	}
}