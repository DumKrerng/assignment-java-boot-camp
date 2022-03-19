package com.example.shopping.order;

import org.jetbrains.annotations.*;

public enum OrderStatus {
	Created,
	Paid;

	public static @Nullable OrderStatus getOrderStatus(String p_name) {
		switch(p_name) {
			case "Created":
				return Created;

			case "Paid":
				return Paid;
		}

		return null;
	}
}
