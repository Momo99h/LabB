/**
 * 
 * Progetto laboratorio B
 * 
 * Mohamed Hussein,   737787
 * Anrea Girola,      740724
 * Vanessa Squillace, 728078
 * Simone Spagnolo,   737742
 * 
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.common.RemoteObserver;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Rappresenza l'oggetto client e la sua interfaccia
 * @author Momo
 */
public class ClientObserver implements Observer, Serializable {

        private static final long serialVersionUID = 1L;

        private RemoteObserver ro = null;
        /**
         * Costruttore
         * @param ro Interfaccia del client
         */
        public ClientObserver(RemoteObserver ro) {
            this.ro = ro;
        }
        /**
         * Non usato!
         * @param o
         * @param arg 
         */
        @Override
        public void update(Observable o, Object arg) 
        {
            
        }
        /**
         * Ottiene l'interfaccia del client
         * @return Interfaccia del client
         */
        public RemoteObserver getOb()
        {
            return this.ro;
        }
        /**
         * Ottiene l'identificativo dell'interfaccia del client
         * @return HashCode dell'interfaccia
         */
        public int getObId()
        {
            return this.ro.hashCode();
        }
}