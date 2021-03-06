package org.jfxworks.connect44fx.templates;

/*###################################################################
### This JavaFX document was generated by Inkscape
### http://www.inkscape.org
### Created: Fri Dec 18 14:12:17 2009
### Version: 0.47pre4 r22446
#####################################################################
### NOTES:
### ============
### JavaFX information can be found at
### http://www.javafx.com/
###
### If you have any problems with this output, please see the
### Inkscape project at http://www.inkscape.org, or visit
### the #inkscape channel on irc.freenode.net . 
###
###################################################################*/


/*###################################################################
##   Exports in this file
##==========================
##    Shapes   : 1
##    Nodes    : 20
###################################################################*/


import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import javafx.scene.paint.*;



public class Level1Cell extends CustomNode {
    /* create LinearGradient for linearGradient2825 */
    function linearGradient2825(): LinearGradient {
        LinearGradient {
            stops:
                [
                Stop {
                    offset: 0.00000000
                    color: Color.rgb(0x00, 0x00, 0xff, 1.00000000)
                },
                Stop {
                    offset: 1.00000000
                    color: Color.rgb(0x00, 0x00, 0xff, 0.00000000)
                },
            ]
        };
    } // end LinearGradient: linearGradient2825


    /* create LinearGradient for linearGradient2831 */
    function linearGradient2831(): LinearGradient {
        LinearGradient {
            stops:
                [
                Stop {
                    offset: 0.00000000
                    color: Color.rgb(0x00, 0x00, 0xff, 1.00000000)
                },
                Stop {
                    offset: 1.00000000
                    color: Color.rgb(0x00, 0x00, 0xff, 0.00000000)
                },
            ]
        };
    } // end LinearGradient: linearGradient2831


    /** path cell */
    function cell() : Path {
        Path {
            id: "cell"
            opacity: 1.00000000
            fill: linearGradient2831()
            stroke: Color.rgb(0x00, 0x00, 0x00, 1.00000000)
            strokeWidth: 0.00000000
            strokeLineCap: StrokeLineCap.BUTT
            strokeLineJoin: StrokeLineJoin.MITER
            strokeMiterLimit: 4.00000000
            elements: [
                MoveTo {
                    x: 0.00000000
                    y: -1049.98718262
                },
                LineTo {
                    x: 0.00000000
                    y: -919.98718262
                },
                LineTo {
                    x: 130.00000000
                    y: -919.98718262
                },
                LineTo {
                    x: 130.00000000
                    y: -1049.98718262
                },
                LineTo {
                    x: 0.00000000
                    y: -1049.98718262
                },
                LineTo {
                    x: 0.00000000
                    y: -1049.98718262
                },
                ClosePath {},
                MoveTo {
                    x: 65.00000000
                    y: -1029.98718262
                },
                CubicCurveTo {
                    controlX1: 92.61423700
                    controlY1: -1029.98718262
                    controlX2: 115.00000000
                    controlY2: -1009.83999662
                    x: 115.00000000
                    y: -984.98718262
                },
                CubicCurveTo {
                    controlX1: 115.00000000
                    controlY1: -960.13436862
                    controlX2: 92.61423700
                    controlY2: -939.98718262
                    x: 65.00000000
                    y: -939.98718262
                },
                CubicCurveTo {
                    controlX1: 37.38576300
                    controlY1: -939.98718262
                    controlX2: 15.00000000
                    controlY2: -960.13436862
                    x: 15.00000000
                    y: -984.98718262
                },
                CubicCurveTo {
                    controlX1: 15.00000000
                    controlY1: -1009.83999662
                    controlX2: 37.38576300
                    controlY2: -1029.98718262
                    x: 65.00000000
                    y: -1029.98718262
                },
                LineTo {
                    x: 65.00000000
                    y: -1029.98718262
                },
                ClosePath {},
            ] // elements
        }; // Path
    } // end path cell

   override function create(): Node {
       Group {
           content: [
               cell(),
           ] // content
           transforms: Translate { x : 25.00000000, y : 1074.98718262 }
       } // Group
   } // function create()
} // class Level1Cell


/*###################################################################
### E N D   C L A S S    Level1Cell
###################################################################*/


