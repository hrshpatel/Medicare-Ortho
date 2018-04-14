package com.ortho.medicare.medicareortho.customviews;

/**
 * {@link CustomEnum} : this class is responsible to handle types of fonts
 *
 * @author Harsh Patel
 * @version : 0.0.1
 * @Date : 21/09/2017
 */
public class CustomEnum {

    public enum CustomFontType {

        BOLD(0),
        REGULAR(2),
        BLACK(3),
        TOAST(4),
        DIALOG(5),
        SEMI_BOLD(6);

        private int id;

        CustomFontType(int id) {
            this.id = id;
        }

        static CustomFontType fromId(int id) {
            for (CustomFontType f : values()) {
                if (f.id == id) return f;
            }
            throw new IllegalArgumentException();
        }

        public int getValue() {
            return id;
        }
    }

    public enum CustomErrorHandleType {
        TOAST(0),
        DIALOG(1);

        private int id;

        CustomErrorHandleType(int id) {
            this.id = id;
        }

        static CustomErrorHandleType fromId(int id) {
            for (CustomErrorHandleType f : values()) {
                if (f.id == id) return f;
            }
            throw new IllegalArgumentException();
        }

        public int getValue() {
            return id;
        }
    }
}
