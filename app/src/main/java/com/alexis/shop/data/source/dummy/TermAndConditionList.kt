package com.alexis.shop.data.source.dummy

import com.alexis.shop.domain.model.faq.TermAndCondition

/**
 * Created by Uwais Alqadri on July 15, 2021
 */
fun getTermList(): List<TermAndCondition> {
	return arrayListOf(
		TermAndCondition(
			"1. Introduction",
			"This document (together with any documents herein mentioned) sets forth the terms and conditions governing the " +
					"use of this website and the purchase of items through such website (hereinafter, the \"Terms\"). Please read through " +
					"these Terms, our Cookies Policy and our Privacy Policy (together the \"Data Protection Policies\") prior to using this " +
					"website. By using this website or placing an order through it, you are consenting to be bound by these Terms and our " +
					"Data Protection Policies. If you do not agree to all of the Terms and the Data Protection Policies, do not use this " +
					"website. These Terms and the Data Protection Policies may be amended. It is your responsibility to regularly read " +
					"through them, as the Terms and the Data Protection Policies in force at the time that you use the website or at the " +
					"time of the formation of the Contract (as defined below) shall be the applicable ones."
		),
		TermAndCondition(
			"2. Our Details",
			"For sale of items through this website, you contract is with Stradivarius UK Limited, a UK company with registered " +
					"office at Lumina House, 89 New Bond Street, London W1S 1DA, Company registration number 08976888 with VAT No. " +
					"GB 186 9704 55. You may contact our customer service department on our freephone number 0808 234 0211, or by " +
					"emailing contact@stradivarius.com, or by filling out the contact form on this website."
		),
		TermAndCondition(
			"3. Your Details and Your Visits to This Website",
			"The information or personal details that you provide us with shall be processed pursuant to the Data Protection " +
					"Policies. By using this website you are consenting to the processing of such information and details and you " +
					"represent that the whole information or details you have provided us with are true and accurate."
		),
		TermAndCondition(
			"4. Use of Our Website",
			"By using this website and/or by placing any order through it, you undertake:" +
					"\n1. To use the website exclusively to make legitimate inquiries or orders." +
					"\n2. Not to make any speculative, false or fraudulent orders. If we are reasonably of the opinion that such an order has " +
					"been made we shall be entitled to cancel the order and inform the relevant" +
					"\n3. To provide correct and accurate e-mail, postal and/or other contact details to us and acknowledge that we may " +
					"use these details to contact you in the event that this should prove necessary (see our Data Protection Policies). " +
					"If you do not give us all of the information that we need, we may not be able to complete your order. By placing an " +
					"order through the website, you warrant that you are at least 18 years old and are legally capable of entering into " +
					"binding contracts."
		),
		TermAndCondition(
			"5. Service Availability",
			"Items offered over this website are only available for delivery to the United Kingdom (Mainland only). If you wish to " +
					"order items from another EU member state outside of the UK via this website, you are of course welcome to do so, " +
					"however the ordered it"
		)
	)
}