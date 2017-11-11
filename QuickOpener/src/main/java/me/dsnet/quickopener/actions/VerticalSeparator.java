/*
 * Copyright (C) 2017 Diego Zambelli Sessona (diego.sessona@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.dsnet.quickopener.actions;

import java.awt.Dimension;
import javax.swing.JSeparator;

/**
 *
 * @author SessonaD
 */
public class VerticalSeparator extends JSeparator {
 
    public VerticalSeparator() {
        super(JSeparator.VERTICAL);
    }
 
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(getPreferredSize().width, super.getMaximumSize().height);
    }
 
    @Override
    public Dimension getSize() {
        return new Dimension(getPreferredSize().width, super.getSize().height);
    }
    
}
