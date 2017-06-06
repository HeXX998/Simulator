package logic.components;

public enum Operation {
	ADD,
	SUBSTRACT,
	MULTIPLICATION,
	DIVISION,
	LOAD,
	STORE;
	public static String OperationAbbr(Operation op)
	{
		switch(op)
		{
		case ADD:
			return "ADDD";
		case SUBSTRACT:
			return "SUBD";
		case MULTIPLICATION:
			return "MULD";
		case DIVISION:
			return "DIVD";
		case LOAD:
			return "LD";
		case STORE:
			return "ST";
		}
		return "";
	}
}
