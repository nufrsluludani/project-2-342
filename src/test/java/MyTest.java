import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	@Test
	void test() {
		BaccaratDealer test = new BaccaratDealer();

		test.generateDeck();

		System.out.println(test.dealHand().get(0).getSuite() + test.dealHand().get(0).getValue());

	}

}
