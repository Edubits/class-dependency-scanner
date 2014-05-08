package com.github.janbeernink.classdependencyscanner;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsCollectionContaining;

public final class Matchers {

	private Matchers() {
	}

	public static Matcher<DependencyGraphNode> hasDependencies(final Class<?>... classes) {
		DependencyGraphNode[] nodes = new DependencyGraphNode[classes.length];
		for (int i = 0; i < classes.length; i++) {
			nodes[i] = new DependencyGraphNode(classes[i]);
		}

		final Matcher<Iterable<DependencyGraphNode>> matcher = IsCollectionContaining.hasItems(nodes);

		return new BaseMatcher<DependencyGraphNode>() {

			@Override
			public boolean matches(Object item) {
				return matcher.matches(((DependencyGraphNode) item).getDependencies());
			}

			@Override
			public void describeMismatch(Object item, Description description) {
				List<String> dependencies = new ArrayList<>();
				for (DependencyGraphNode node: ((DependencyGraphNode)item).getDependencies()) {
					dependencies.add(node.getNodeClass().getName());
				}

				description.appendValueList("depends on: ", ", ", ".", dependencies);
			}

			@Override
			public void describeTo(Description description) {
				List<String> classNames = new ArrayList<>();
				for (Class<?> clazz : classes) {
					classNames.add(clazz.getName());
				}

				description.appendValueList("depends on: ", ", ", ".", classNames);
			}
		};
	}
}