# Tiny-Gates
Tiny Gates is an add-on for [Tiny Redstone](https://www.curseforge.com/minecraft/mc-mods/tiny-redstone "Tiny Redstone") that adds tiny redstone gates that you can put on panels to form tiny integrated circuits.


This mod adds the following tiny gates for use on redstone panels:
### Tiny AND Gate
Outputs to front when a signal is received to both left AND right side.

###Tiny OR Gate
Outputs to front when a signal is received to either left OR right side.

###Tiny XOR Gate
Outputs to front when the input signals do not match, similar to a 3-way light switch.

###Tiny NOT Gate"
Outputs to front when there is no signal to the back. Similar to redstone torch but to single direction with no delay.

###Tiny RS Latch"
Outputs to front when signal on left (Set signal) is pulsed. Remains on until signal on right (Reset signal) is pulsed.

###Tiny T Flip Flop
Output to front toggles on pulse to back.

###Tiny Counter
Counts pulses received to back input (up to 15). Displays number and outputs that signal strength to front.
Also can act as a potentiometer.
Input to left locks number. Input to right resets counter.

###Tiny Clock
Outputs redstone pulses to front at a set interval.
Right click for GUI to set interval.
Disable with redstone signal to back.

###Tiny Edge Detector
Outputs redstone pulse at start (rising edge) or stop (falling edge) of redstone signal to back input.
Right click to toggle edge to detect (rising or falling).
