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

/**
 * Represents an IceCream cone.
 * 
 * @author Zach Kissel
 */
public class IceCreamCone 
{
    private enum Flavor {CHOCOLALTE, VANILLA, STRAWBERRY};
    private enum ConeType {SUGAR, WAFFLE, CAKE};
    private Flavor flv;
    private ConeType ctype;
    private boolean hasCherry;
    private boolean hasSprinkles;
    private boolean hasWhippedCream;

     /**
     * Construct a new ice cream code from a IceCreamCone builder.
     * @param b the builder that specifies how to construct the ice cream cone.
     */
    private IceCreamCone(IceCreamConeBuilder b)
    {
        this.ctype = b.ctype;
        this.flv = b.flv;
        this.hasCherry = b.hasCherry;
        this.hasWhippedCream = b.hasWhippedCream;
        this.hasSprinkles = b.hasSprinkles;
    }

    /** 
     * Converts the icecream cone to a string.
     */
    @Override
    public String toString()
    {
        return "Flavor: " + flv + "\nCone: " + ctype + "\nWith Cherry: " + hasCherry + 
           "\nWith Whipped Cream: " + hasWhippedCream + "\nWith Sprinkles: " + hasSprinkles;
    }

    /**
     * Builds a valid ice cream cone, or a valid default cone.
     */
    public static class IceCreamConeBuilder 
    {
        private Flavor flv = Flavor.VANILLA;
        private ConeType ctype = ConeType.SUGAR;
        private boolean hasCherry = false;
        private boolean hasSprinkles = false;
        private boolean hasWhippedCream = false;

        /**
         * Use a sugar cone.
         * @return an ice cream cone that uses a sugar cone.
         */
        public IceCreamConeBuilder sugarCone()
        {
            this.ctype = ConeType.SUGAR;
            return this;
        }

        /**
         * Use a waffle cone.
         * @return an ice cream cone that use a waffle cone.
         */
        public IceCreamConeBuilder waffleCone()
        {
            this.ctype = ConeType.WAFFLE;
            return this;
        }

        /**
         * Use a cake cone.
         * @return an ice cream cone that uses a cake cone.
         */
        public IceCreamConeBuilder cakeCone()
        {
            this.ctype = ConeType.CAKE;
            return this;
        }

        /**
         * Use vanilla ice cream.
         * @return an ice cream cone with vanilla ice cream.
         */
        public IceCreamConeBuilder vanilla()
        {
            this.flv = Flavor.VANILLA;
            return this;
        }

        /**
         * Use chocolate ice cream
         * @return an ice cream cone with chocolate ice cream.
         */
        public IceCreamConeBuilder chocolate()
        {
            this.flv = Flavor.CHOCOLALTE;
            return this;
        }

        /**
         * Use strawberry ice cream.
         * @return an ice cream cone with strawberry ice cream.
         */
        public IceCreamConeBuilder strawberry()
        {
            this.flv = Flavor.STRAWBERRY;
            return this;
        }

        /**
         * Add a cherry. 
         * @return an ice cream cone with a cherry.
         */
        public IceCreamConeBuilder addCherry()
        {
            this.hasCherry = true;
            return this;
        }

        /**
         * Add whipped cream.
         * @return an ice cream cone with whipped cream.
         */
        public IceCreamConeBuilder addWhippedCream()
        {
            this.hasWhippedCream = true;
            return this;
        }

        /**
         * Add sprinkles 
         * @return an ice cream cone with sprinkles.
         */
        public IceCreamConeBuilder addSprinkles()
        {
            this.hasSprinkles = true;
            return this;
        }

        /**
         * Build the ice cream cone as specified.
         * @return a new IceCreamCone.
         */
        public IceCreamCone build()
        {
            return new IceCreamCone(this);
        }
    };

   
}
