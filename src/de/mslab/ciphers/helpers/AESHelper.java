package de.mslab.ciphers.helpers;

import de.mslab.core.ByteArray;
import de.mslab.core.Differential;

abstract class AESHelper extends AbstractCipherHelper {
	
	public int countActiveComponents(Differential stateDifferential, Differential keyDifferential) {
		ByteArray stateDifference;
		int sum = 0;
		int fromRound = stateDifferential.fromRound;
		int toRound = stateDifferential.toRound;
		
		if (fromRound == 1) {
			stateDifference = stateDifferential.getIntermediateStateDifference(0).getDelta();
			sum += stateDifference.countNumActiveBytes();
			sum += countActiveKeyBytes(0, keyDifferential.getKeyDifference(0).getDelta());
		} else {
			stateDifference = stateDifferential.getStateDifference(fromRound - 1).getDelta();
			sum += stateDifference.countNumActiveBytes();
		}
		
		for (int round = fromRound; round < toRound; round++) {
			stateDifference = stateDifferential.getStateDifference(round).getDelta();
			sum += stateDifference.countNumActiveBytes();
		}
		
		for (int round = fromRound; round <= toRound; round++) {
			sum += countActiveKeyBytes(round, keyDifferential.getKeyDifference(round).getDelta());
		}
		
		return sum;
	}
	
	public boolean shareActiveComponents(Differential deltaDifferential, Differential nablaDifferential) {
		int fromRound = deltaDifferential.fromRound;
		int toRound = deltaDifferential.toRound;
		
		if (fromRound == 1) {
			if (checkIntermediateState(0, deltaDifferential, nablaDifferential)
				|| checkKey(0, deltaDifferential, nablaDifferential)) {
				return true;
			}
		} else {
			if (checkState(fromRound - 1, deltaDifferential, nablaDifferential)) {
				return true;
			}
		}
		
		for (int round = fromRound; round < toRound; round++) {
			if (checkState(round, deltaDifferential, nablaDifferential)) {
				return true;
			}
		}
		
		for (int round = fromRound; round <= toRound; round++) {
			if (checkKey(round, deltaDifferential, nablaDifferential)) {
				return true;
			}
		}
		
		return false;
	}
	
	protected abstract int countActiveKeyBytes(int round, ByteArray key);
	
}