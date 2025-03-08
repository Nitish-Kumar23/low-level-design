package SolidPrinciple;

public class MacBook {

    private WiredMouse wiredMouse;
    private BluetoothKeyboard bluetoothKeyboard;

    private Mouse mouse;

    private Keyboard keyboard;

    /**
     *
     * this is wrong because we're working with concrete classes instead of interfaces, became hardcoded difficult to change mouse/keyboard type
     * violates dependency inversion principle
     *
     * @param wiredMouse
     * @param bluetoothKeyboard
     */
    public MacBook(WiredMouse wiredMouse, BluetoothKeyboard bluetoothKeyboard) {
        this.wiredMouse = wiredMouse;
        this.bluetoothKeyboard = bluetoothKeyboard;
    }

    /**
     * use this always depend on interfaces instead of concrete classes now we can pass any class which implements either keyboard or mouse
     * @param mouse
     * @param keyboard
     */
    public MacBook(Mouse mouse, Keyboard keyboard) {
        this.mouse = mouse;
        this.keyboard = keyboard;
    }
}
