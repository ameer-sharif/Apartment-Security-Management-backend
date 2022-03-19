package com.cg.aps.exception;

//@SuppressWarnings("serial")
public class VehicleIdNotFoundException extends RuntimeException {
		private String name;

		public VehicleIdNotFoundException() {
			// TODO Auto-generated constructor stub
		}

		public VehicleIdNotFoundException(String vehi) {
			super(vehi);
		}


	}