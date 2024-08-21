
//CSS
import '../CSS/Pricing.css'

export default function Pricing() {
    return (
        <div className="pricing-container">
            <h1 className="pricing-title">Our Pricing Plans</h1>
            <div className="pricing-cards">
                {/* Basic Plan */}
                <div className="pricing-card">
                    <h2>Basic Plan</h2>
                    <p className="price">$9.99/month</p>
                    <ul>
                        <li>5 GB Storage</li>
                        <li>Email Support</li>
                        <li>Community Access</li>
                    </ul>
                    <button className="btn">Choose Plan</button>
                </div>

                {/* Standard Plan */}
                <div className="pricing-card">
                    <h2>Standard Plan</h2>
                    <p className="price">$19.99/month</p>
                    <ul>
                        <li>50 GB Storage</li>
                        <li>Priority Support</li>
                        <li>Access to all features</li>
                    </ul>
                    <button className="btn">Choose Plan</button>
                </div>

                {/* Premium Plan */}
                <div className="pricing-card">
                    <h2>Premium Plan</h2>
                    <p className="price">$49.99/month</p>
                    <ul>
                        <li>Unlimited Storage</li>
                        <li>24/7 Priority Support</li>
                        <li>Advanced Features</li>
                    </ul>
                    <button className="btn">Choose Plan</button>
                </div>
            </div>
        </div>
    );
}
