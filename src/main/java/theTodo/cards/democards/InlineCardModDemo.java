package theTodo.cards.democards;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Spiker;
import theTodo.cardmods.LambdaMod;
import theTodo.cards.AbstractTodoCard;

import static theTodo.TodoMod.makeID;
import static theTodo.util.Wiz.makeInHand;
import static theTodo.util.Wiz.returnTrulyRandomPrediCardInCombat;

public class InlineCardModDemo extends AbstractTodoCard {

    public final static String ID = makeID("InlineCardModDemo");
    // intellij stuff skill, self, uncommon

    public InlineCardModDemo() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = returnTrulyRandomPrediCardInCombat(c -> c.hasTag(CardTags.STRIKE) && c.rarity != CardRarity.BASIC, true);
        CardModifierManager.addModifier(q, new LambdaMod() {
            @Override
            public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
                if (target instanceof Spiker) {
                    return 999;
                }
                return damage;
            }

            @Override
            public String modifyDescription(String rawDescription, AbstractCard card) {
                return rawDescription + " NL Kills Spikers.";
            }
        });
        makeInHand(q);
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}