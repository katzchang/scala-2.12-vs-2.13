SCALA_VERSIONS=2.12.10 2.13.1

test:
	-./sbt "++ 2.12.10 -v test"
	-./sbt "++ 2.13.1 -v test"
