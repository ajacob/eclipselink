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
public final class KeywordExpressionTest extends AbstractJPQLTest
{
	@Override
	boolean isTolerant()
	{
		return true;
	}

	@Test
	public void testBuildExpression_01()
	{
		String query = "UPDATE Employee e SET e.isEnrolled = TRUE";
		JPQLExpression jpqlExpression = JPQLTests.buildQuery(query);

		// UpdateStatement
		Expression expression = jpqlExpression.getQueryStatement();
		assertTrue(expression instanceof UpdateStatement);
		UpdateStatement updateStatement = (UpdateStatement) expression;

		// UpdateClause
		expression = updateStatement.getUpdateClause();
		assertTrue(expression instanceof UpdateClause);
		UpdateClause updateClause = (UpdateClause) expression;

		// UpdateItem
		expression = updateClause.getUpdateItems();
		assertTrue(expression instanceof UpdateItem);
		UpdateItem updateItem = (UpdateItem) expression;

		// KeywordExpression
		expression = updateItem.getNewValue();
		assertTrue(expression instanceof KeywordExpression);
		KeywordExpression keywordExpression = (KeywordExpression) expression;

		assertEquals(KeywordExpression.TRUE, keywordExpression.getText());
	}

	@Test
	public void testBuildExpression_02()
	{
		String query = "UPDATE Employee e SET e.isEnrolled = FALSE";
		JPQLExpression jpqlExpression = JPQLTests.buildQuery(query);

		// UpdateStatement
		Expression expression = jpqlExpression.getQueryStatement();
		assertTrue(expression instanceof UpdateStatement);
		UpdateStatement updateStatement = (UpdateStatement) expression;

		// UpdateClause
		expression = updateStatement.getUpdateClause();
		assertTrue(expression instanceof UpdateClause);
		UpdateClause updateClause = (UpdateClause) expression;

		// UpdateItem
		expression = updateClause.getUpdateItems();
		assertTrue(expression instanceof UpdateItem);
		UpdateItem updateItem = (UpdateItem) expression;

		// KeywordExpression
		expression = updateItem.getNewValue();
		assertTrue(expression instanceof KeywordExpression);
		KeywordExpression keywordExpression = (KeywordExpression) expression;

		assertEquals(KeywordExpression.FALSE, keywordExpression.getText());
	}

	@Test
	public void testBuildExpression_03()
	{
		String query = "UPDATE Employee e SET e.manager = NULL";
		JPQLExpression jpqlExpression = JPQLTests.buildQuery(query);

		// UpdateStatement
		Expression expression = jpqlExpression.getQueryStatement();
		assertTrue(expression instanceof UpdateStatement);
		UpdateStatement updateStatement = (UpdateStatement) expression;

		// UpdateClause
		expression = updateStatement.getUpdateClause();
		assertTrue(expression instanceof UpdateClause);
		UpdateClause updateClause = (UpdateClause) expression;

		// UpdateItem
		expression = updateClause.getUpdateItems();
		assertTrue(expression instanceof UpdateItem);
		UpdateItem updateItem = (UpdateItem) expression;

		// KeywordExpression
		expression = updateItem.getNewValue();
		assertTrue(expression instanceof KeywordExpression);
		KeywordExpression keywordExpression = (KeywordExpression) expression;

		assertEquals(KeywordExpression.NULL, keywordExpression.getText());
	}

	@Test
	public void testBuildExpression_04()
	{
		String query = "SELECT e FROM Employee e WHERE e.hired = TRUE";
		JPQLExpression jpqlExpression = JPQLTests.buildQuery(query);

		// SelectStatement
		Expression expression = jpqlExpression.getQueryStatement();
		assertTrue(expression instanceof SelectStatement);
		SelectStatement selectStatement = (SelectStatement) expression;

		// WhereClause
		expression = selectStatement.getWhereClause();
		assertTrue(expression instanceof WhereClause);
		WhereClause whereClause = (WhereClause) expression;

		// ComparisonExpression
		expression = whereClause.getConditionalExpression();
		assertTrue(expression instanceof ComparisonExpression);
		ComparisonExpression comparisonExpression = (ComparisonExpression) expression;

		// KeywordExpression
		expression = comparisonExpression.getRightExpression();
		assertTrue(expression instanceof KeywordExpression);
		KeywordExpression keywordExpression = (KeywordExpression) expression;

		assertEquals(KeywordExpression.TRUE, keywordExpression.getText());
	}
}