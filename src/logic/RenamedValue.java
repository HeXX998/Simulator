package logic;

import logic.components.FPRegisterFile;

public class RenamedValue {
	public boolean isAvailable;
	public float value;
	public ReservationStation.Entry rsEntry;
	public RenamedValue(FPRegisterFile fpRegisterFile, int index)
	{
		if(fpRegisterFile.getUpdatedValue(index) == null)
		{
			isAvailable = true;
			value = fpRegisterFile.getData(index);
		}
		else
		{
			isAvailable = false;
			rsEntry = fpRegisterFile.getUpdatedValue(index);
		}
	}
}
