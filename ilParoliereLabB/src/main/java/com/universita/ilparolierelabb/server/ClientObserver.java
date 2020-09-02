/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

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