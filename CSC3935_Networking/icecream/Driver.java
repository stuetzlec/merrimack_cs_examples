/*
 *   Copyright (C) 2022 -- 2023  Zachary A. Kissel
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
public class Driver 
{
    /**
     * Builds a simple ice cream cone using the builder pattern
     */
    public static void main(String[] args)
    {
        // Create a icecream cone with a sugar cone, chocolate ice cream with a cherry and whipped cream.
        IceCreamCone cone = new IceCreamCone.IceCreamConeBuilder().sugarCone().chocolate().addCherry().addWhippedCream().build();
        System.out.println("Cone is: \n" + cone);
        System.out.println("\n\n");
        
        // Construct a default Icecream cone.
        IceCreamCone defaultCone = new IceCreamCone.IceCreamConeBuilder().build();
        System.out.println("Defualt is: \n" + defaultCone);
    }   
}