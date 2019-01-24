/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.architecture.messagebus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Facade per l'invio dei messaggi ai client remoti.
 * 
 * @author mario
 */
public interface MessageBus {
	void broadcast( @NotEmpty String destinationTopic, @NotNull Object what );

	void broadcast( @NotNull Object what );
}