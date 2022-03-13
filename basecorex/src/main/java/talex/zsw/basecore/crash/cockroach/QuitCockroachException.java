package talex.zsw.basecore.crash.cockroach;

final class QuitCockroachException extends RuntimeException
{
	public QuitCockroachException(String message)
	{
		super(message);
	}
}
