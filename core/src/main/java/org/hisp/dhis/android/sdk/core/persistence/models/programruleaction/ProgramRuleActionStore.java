/*
 *  Copyright (c) 2015, University of Oslo
 *  * All rights reserved.
 *  *
 *  * Redistribution and use in source and binary forms, with or without
 *  * modification, are permitted provided that the following conditions are met:
 *  * Redistributions of source code must retain the above copyright notice, this
 *  * list of conditions and the following disclaimer.
 *  *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *  * this list of conditions and the following disclaimer in the documentation
 *  * and/or other materials provided with the distribution.
 *  * Neither the name of the HISP project nor the names of its contributors may
 *  * be used to endorse or promote products derived from this software without
 *  * specific prior written permission.
 *  *
 *  * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package org.hisp.dhis.android.sdk.core.persistence.models.programruleaction;

import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.hisp.dhis.android.sdk.core.persistence.models.flow.ProgramRuleAction$Flow;
import org.hisp.dhis.android.sdk.core.persistence.models.flow.ProgramRuleAction$Flow$Table;
import org.hisp.dhis.android.sdk.models.programrule.ProgramRule;
import org.hisp.dhis.android.sdk.models.programruleaction.IProgramRuleActionStore;
import org.hisp.dhis.android.sdk.models.programruleaction.ProgramRuleAction;

import java.util.List;

public final class ProgramRuleActionStore implements IProgramRuleActionStore {

    public ProgramRuleActionStore() {
        //empty constructor
    }

    @Override
    public void insert(ProgramRuleAction object) {
        ProgramRuleAction$Flow programRuleActionFlow = ProgramRuleAction$Flow.fromModel(object);
        programRuleActionFlow.insert();

        object.setId(programRuleActionFlow.getId());
    }

    @Override
    public void update(ProgramRuleAction object) {
        ProgramRuleAction$Flow.fromModel(object).update();
    }

    @Override
    public void save(ProgramRuleAction object) {
        ProgramRuleAction$Flow programRuleActionFlow =
                ProgramRuleAction$Flow.fromModel(object);
        programRuleActionFlow.save();

        object.setId(programRuleActionFlow.getId());
    }

    @Override
    public void delete(ProgramRuleAction object) {
        ProgramRuleAction$Flow.fromModel(object).delete();
    }

    @Override
    public List<ProgramRuleAction> query() {
        List<ProgramRuleAction$Flow> programRuleActionFlows = new Select()
                .from(ProgramRuleAction$Flow.class)
                .queryList();
        return ProgramRuleAction$Flow.toModels(programRuleActionFlows);
    }

    @Override
    public ProgramRuleAction query(long id) {
        ProgramRuleAction$Flow programRuleActionFlow = new Select()
                .from(ProgramRuleAction$Flow.class)
                .where(Condition.column(ProgramRuleAction$Flow$Table.ID).is(id))
                .querySingle();
        return ProgramRuleAction$Flow.toModel(programRuleActionFlow);
    }

    @Override
    public ProgramRuleAction query(String uid) {
        ProgramRuleAction$Flow programRuleActionFlow = new Select()
                .from(ProgramRuleAction$Flow.class)
                .where(Condition.column(ProgramRuleAction$Flow$Table.UID).is(uid))
                .querySingle();
        return ProgramRuleAction$Flow.toModel(programRuleActionFlow);
    }

    @Override
    public List<ProgramRuleAction> query(ProgramRule programRule) {
        List<ProgramRuleAction$Flow> programRuleActionFlows = new Select()
                .from(ProgramRuleAction$Flow.class).where(Condition
                        .column(ProgramRuleAction$Flow$Table.PROGRAMRULE).is(programRule.getUId()))
                .queryList();
        return ProgramRuleAction$Flow.toModels(programRuleActionFlows);
    }
}