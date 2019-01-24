/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.architecture.messagebus;

/**
 * @author mario
 */
public class EntityCreatedMessage implements TypedMessage {

	private Object entity;
	
	public EntityCreatedMessage( Object entity ) {
		this.entity = entity;
	}

	public Object getEntity() {
		return entity;
	}

	/* (non-Javadoc)
	 * @see it.linksmt.teamshare.architecture.messagebus.TypedMessage#getType()
	 */
	@Override
	public String getType() {
		return entity.getClass().getSimpleName().replaceAll( "Dto", "" ) + "Created";
	}
}
